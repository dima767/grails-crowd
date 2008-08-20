import net.sf.textile4j.Textile

    class TextileCodec {
        static encode = { str ->
            return new Textile().process(str)
        }
    }

