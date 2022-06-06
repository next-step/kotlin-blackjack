package dsl.language

data class Languages(val languages: List<Language>) {
    fun get(index: Int): Language {
        return this.languages[index]
    }
}
