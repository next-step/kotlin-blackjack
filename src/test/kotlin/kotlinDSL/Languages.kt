package kotlinDSL

class Languages {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is Languages){
            return false
        }
        return languages == other.languages
    }
}