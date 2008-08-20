package grailscrowd.capability

/** 'Value class' model that encapsulates basic info about
    'Web impression' (public web view) of any resource in the system
*/
class WebImpressionInfo {

    String fromIpAddress
    String whenOccurred
    String forWhichResource
    Long resourceId
}