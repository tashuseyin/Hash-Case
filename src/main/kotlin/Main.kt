import java.math.BigInteger
import java.security.MessageDigest

fun main(args: Array<String>) {
    val listOfHashes = mutableListOf<String>()
    val listOfCharacters = mutableListOf<Char>()
    var listOfDecryptedHashes = ""
    var counter = 0
    var word = ""
    val decrypt = Decrypt()
    val model = decrypt.parseJson()

    model?.hash?.forEach {
        counter++
        if (counter == 33) {
            listOfHashes.add(word)
            word = ""
            counter = 1
        }
        word += it

    }
    listOfHashes.add(word)

    for (i in 33..95) {
        if (i > 64) {
            listOfCharacters.add(i.toChar().lowercaseChar())

        } else {
            listOfCharacters.add(i.toChar())
        }
    }

    listOfHashes.forEach { hash ->
        listOfDecryptedHashes += model?.email
            ?.let { email -> findHash(listOfCharacters, listOfDecryptedHashes, hash, email) }
    }

    print(listOfDecryptedHashes)
}

fun findHash(
    listOfCharacters: List<Char>,
    listOfDecryptedHashes: String,
    hash: String,
    mail: String
): String {
    listOfCharacters.forEach { char ->
        listOfCharacters.forEach { secondChar ->
            val string = listOfDecryptedHashes + char + secondChar
            val hashed = md5(md5(mail) + string + md5(string))
            if (hash.contains(hashed)) {
                return char.toString() + secondChar.toString()
            }
        }
    }
    return ""
}

fun md5(input: String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}
