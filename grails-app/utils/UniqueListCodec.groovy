class UniqueListCodec {

    static encode = {str ->
        if (str.indexOf(',')) {
            return str.tokenize(',').collect {it.toLowerCase().trim().replaceAll(' ', '')}.unique()
        }
        else {
            return [str.replaceAll(' ', '')]
        }
    }
}