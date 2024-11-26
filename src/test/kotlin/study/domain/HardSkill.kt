package study.domain

data class HardSkill(val description: String) {
    init {
        require(description.isNotBlank()) { "하드 스킬은 1글자 이상이어야 합니다." }
    }
}
