import org.joda.time.*

class NiceDateTagLib {

	public static String getNiceDate(Date date) {

        def now = new Date()

        def diff = Math.abs(now.getTime() - date.getTime())

        long second = 1000
        long minute = 1000 * 60
        long hour = minute * 60
        long day = hour * 24

        def niceTime = ""

        long calc = 0L;

        calc = Math.floor(diff / day)
        if (calc > 0) {
            niceTime += calc + " day" + (calc > 1 ? "s " : " ")
            diff = diff % day
        }

        calc = Math.floor(diff / hour)
        if (calc > 0) {
            niceTime += calc + " hour" + (calc > 1 ? "s " : " ")
            diff = diff % hour
        }

        calc = Math.floor(diff / minute)
        if (calc > 0) {
            niceTime += calc + " minute" + (calc > 1 ? "s " : " ")
            diff = diff % minute
        }

        if (niceTime.length() == 0) {
            niceTime = "Right now"
        } else {
            niceTime += (date.getTime() > now.getTime()) ? "from now" : "ago"
        }

        return niceTime

    }

    public static String getNiceDateUsingJoda(Date date) {		
		def from = null
		def to = null
		try {
			from = new DateTime(date)
			to = new DateTime(new Date())
		}
		catch(Exception e) {
			return ''
		}

		Period period = new Period(from, to, PeriodType.yearMonthDayTime())
		def mi = period.minutes
		def h = period.hours
		def d = period.days
		def m = period.months
		def y = period.years
	
		def output = ''
		if(y > 0) output += "$y " + "year" + (y > 1 ? "s " : " ")
		if(m > 0) output += "$m " + "month" + (m > 1 ? "s " : " ")
		if(d > 0) output += "$d " + "day" + (d > 1 ? "s " : " ")
		if(h > 0) output += "$h " + "hour" + (h > 1 ? "s " : " ")
		if(mi > 0) output += "$mi " + "minute" + (mi > 1 ? "s " : " ")
		if(output.size() > 0) {
		    output += ' ago'
		}
		else {
			output += 'less than a minute ago'
		}
		return output
	}

	def niceDate = { attrs, body ->
        def date = attrs.date
        def sdf = new java.text.SimpleDateFormat("EEE, d MMM yyyy")
        out << sdf.format(date)
	}
	
	def niceAgoDate = { attrs, body ->
        out << getNiceDateUsingJoda(attrs.date)
	}
}
