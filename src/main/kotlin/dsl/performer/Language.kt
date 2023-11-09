package dsl.performer

data class Language(
    val lang: String,
    val level: Int,

) {
    override fun toString(): String {
        return "Language(lang=$lang level=$level)"
    }
}


