fun main() {
val alphabet = arrayOf('А','Б','В','Г','Д','Е','Ё','Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ъ','Ы','Ь','Э','Ю','Я' )
val values = arrayOf(21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30, 27, 8, 18, 10, 33, 31, 32, 19, 7, 17)

    println("Выберите операцию: ")
    println("1. Зашифровать текст")
    println("2. Расшифровать текст")
    println("Введите номер операции: ")
    val operation = readLine()?.toInt()

    when (operation) {
        1 -> {
            val keyword = readLine()
            val message = readLine()

            if (keyword != null && message != null){
                val encryptedText = encryptText(message, keyword, alphabet, values)
                println("Зашифрованный текст: $encryptedText")
            } else{
                println("Некорректный ввод")
            }
        }
        2 -> {
            val keyword = readLine()?.toUpperCase()
            val encryptedText = readLine()?.toUpperCase()
            if (keyword != null && encryptedText != null){
                val decryptedText = decryptText(encryptedText, keyword, alphabet, values)
                println("Расшифрованный текст: $decryptedText")
            } else {
                println("Некорректный ввод")
            }
        }
        else -> println("Некорректный ввод")
    }
}


fun encryptText(message: String, keyword: String, alphabet: Array<Char>, values: Array<Int>): String {
    val encryptedText = StringBuilder()

    for (i in message.indices) {
        val messageChar = message[i]
        val keywordChar = keyword[i % keyword.length]
        val keywordIndex = alphabet.indexOf(keywordChar)

        if (keywordIndex != -1) {
            val shiftedIndex = (values[keywordIndex] - 1) % alphabet.size
            val encryptedChar = shiftChar(messageChar, alphabet, shiftedIndex)
            encryptedText.append(messageChar)
        }
    }
    return encryptedText.toString()
}

fun decryptText(encryptedText: String, keyword: String, alphabet: Array<Char>, values: Array<Int>): String {
    val decryptedText = StringBuilder()

    for (i in encryptedText.indices) {
        val encryptedChar = encryptedText[i]
        val keywordChar = keyword[i % keyword.length]
        val keywordIndex = alphabet.indexOf(keywordChar)

        if (keywordIndex != -1) {
            val shiftedIndex = (values[keywordIndex] - 1) % alphabet.size
            val decryptedChar = shiftChar(encryptedChar, alphabet, -shiftedIndex)
            decryptedText.append(decryptedChar)
        } else{
            decryptedText.append(encryptedChar)
        }
    }
return decryptedText.toString()
}

fun shiftChar(char: Char, alphabet: Array<Char>, shift: Int): Char{
    val index = alphabet.indexOf(char)
    val shiftedIndex = (index + shift + alphabet.size) % alphabet.size
    return alphabet[shiftedIndex]
}