package study

class Languages {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(other: Int) {
        languages.add(Language(this, other))
    }

    override fun equals(other: Any?): Boolean {
        return other != null && this.languages == (other as Languages).languages
    }

    override fun hashCode(): Int {
        return languages.hashCode()
    }
}
