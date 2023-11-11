package study

enum class SkillType {
    SOFT, HARD;

    private lateinit var value: String

    fun value(value: String): SkillType {
        this.value = value
        return this
    }
}
