package blackJack.utils

class StringUtils {
    companion object {
        private const val COMMA = ","
        fun splitString(name: String): List<String> = name.split(COMMA)
        fun joinToStrings(strings: List<String>): String = strings.joinToString()
    }
}
