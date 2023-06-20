package dsl.domain

data class Languages(private val languages: List<Language>) {
    val size = languages.size

    init {
        require(languages.isNotEmpty()) { NO_LANGUAGE_ERROR_MESSAGE }
    }

    companion object {
        private const val NO_LANGUAGE_ERROR_MESSAGE = "최소 1개 이상의 언어를 등록해주세요"
    }
}
