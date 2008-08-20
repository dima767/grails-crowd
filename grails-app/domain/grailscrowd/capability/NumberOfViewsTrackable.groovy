package grailscrowd.capability

/** This abstraction allows to encapsulate the responsibility for
    tracking number of web impressions for any resource (entity/model)
    that is interested in this functionality. This also achieves a high
    cohesion of the classes in the system (clear division of responsibilities
*/
abstract class NumberOfViewsTrackable {

    static mapping = {
        tablePerHierarchy false
    }

    def recordOnePublicViewIfEligible(requestIp) {
        withEligibilityCheck(requestIp) {
            new WebImpressionInfo(fromIpAddress: requestIp,
                    forWhichResource: this.class.name,
                    resourceId: id,
                    whenOccurred: new Date().encodeAsMMddyyyyString()).save()
        }
    }

    def getNumberOfPublicViews() {
        WebImpressionInfo.withCriteria {
            and {
                eq('forWhichResource', this.class.name)
                eq('resourceId', id)
            }
        }.size()
    }

    private def withEligibilityCheck(requestIp, capturePublicView) {
        //Hasn't been viewed from the same IP on the same day
        def n = WebImpressionInfo.withCriteria {
            and {
                eq('fromIpAddress', requestIp)
                eq('whenOccurred', new Date().encodeAsMMddyyyyString())
                eq('forWhichResource', this.class.name)
                eq('resourceId', id)
            }
        }.size()
        if (n == 0) {
            //This is a closure passed in from the 'recordOnePublicViewIfEligible' method
            capturePublicView()
        }
    }
}
