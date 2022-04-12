import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileReader


class Decrypt {
    companion object{
        fun readJsonFromAsset(): String? {
            var json: String? = null
            val fileName = "src/main/kotlin/mail.json"
            try {
                val bufferedReader = BufferedReader(FileReader(fileName))
                json = bufferedReader.use { it.readText() }
            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
            return json
        }
    }

    fun parseJson(): Model? {
        return Gson().fromJson(readJsonFromAsset(), Model::class.java)
    }
}