package dsl.language

data class Languages(val languages: MutableList<Language>) {
    fun get(index: Int): Language {
        return this.languages[index]
    }
}
