package camp.nextstep.edu.step.step1.domain

data class Language(
    val name: String,
    val level: Int
) {
    init {
        require(name.isNotBlank()) { "언어 이름이 입력되지 않았습니다." }
        require(level in 1..5) { "언어 레벨은 1~5 사이의 값이어야 합니다." }
    }

}
