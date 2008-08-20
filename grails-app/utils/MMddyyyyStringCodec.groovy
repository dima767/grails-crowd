import java.text.SimpleDateFormat

class MMddyyyyStringCodec {

    private static final DF = new SimpleDateFormat('MMddyyyy')

    static encode = { date ->
        DF.format(date)
    }
}