class ErrorsController {

    def notFound = {
        render(view: 'friendlyError', model: [messageHeader: 'Page Not Found',
                messageBody: 'Perhaps you followed a bad link.'])

    }

    def serverError = {
        render(view: 'friendlyError', model: [messageHeader: 'Something went wrong',
                messageBody: 'Something technically went wrong on our servers.<br />&nbsp;We appologize for the inconvenience.'])
    }

    def notAllowed = {
        render(view: 'friendlyError', model: [messageHeader: 'Do not do this',
                messageBody: 'You are trying to do something that is not allowed.<br />&nbsp;Please use links and buttons for normal navigation.'])
    }
}
