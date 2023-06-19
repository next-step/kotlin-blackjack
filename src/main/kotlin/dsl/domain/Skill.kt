package dsl.domain

data class Skill(private val description: String, private val type: SkillType) {
    init {
        require(description.isNotBlank()) { DESCRIPTION_EMPTY_ERROR_MESSAGE }
    }
    companion object {
        private const val DESCRIPTION_EMPTY_ERROR_MESSAGE = "능력 설명이 비어있을 수 없습니다"
    }
}
enum class SkillType(val value: String) {
    SOFT("soft"), HARD("hard")
}
