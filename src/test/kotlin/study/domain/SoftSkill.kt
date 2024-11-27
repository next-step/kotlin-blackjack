package study.domain

data class SoftSkill(val description: String) {
    init {
        require(description.isNotBlank()) { "소프트 스킬은 1글자 이상이어야 합니다." }
    }
}
