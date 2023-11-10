package dsl

data class Language(
    val name: String,
    val level: Int
) {
    init {
        require(name.isNotBlank()) { "언어 이름은 공백이 아니어야 합니다." }
        require(level >= 1) { "언어 레벨은 1 이상이어야 합니다." }
    }
}
