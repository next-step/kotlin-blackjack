package dsl.domain

data class Language(private val name: String, private val level: Int) {
    init {
        require(name.isNotBlank()) { NAME_EMPTY_ERROR_MESSAGE }
        require(level > 0) { LEVEL_INVALID_ERROR_MESSAGE }
    }

    companion object {
        private const val NAME_EMPTY_ERROR_MESSAGE = "언어명이 비어있을수 없습니다"
        private const val LEVEL_INVALID_ERROR_MESSAGE = "언어 수준이 0이하일 수 없습니다"
    }
}
