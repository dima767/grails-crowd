import net.sf.textile4j.Textile

    class TextileCodec {
		
        static encode = { str ->
            if(str.contains("</script>")) {
				return '--'
			}
			return new Textile().process(str)
        }
    }

