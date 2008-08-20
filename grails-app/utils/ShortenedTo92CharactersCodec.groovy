class ShortenedTo92CharactersCodec {    

    static encode = { str ->
        if(str.size() < 92) {
            return str
        }
        else {
            return "${str[0..91]}..."
        }
    }
}