package study.person

@JvmInline
value class Languages(val languages: MutableList<Language> = mutableListOf()) {
    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }
}
