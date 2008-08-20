import java.text.SimpleDateFormat

class MonthYearCodec {

    private static final DF = new SimpleDateFormat('MMMM yyyy')

    static encode = {date ->
        DF.format(date)
    }

    static decode = {str ->
        DF.parse(str)
    }
}