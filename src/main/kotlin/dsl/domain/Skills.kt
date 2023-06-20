package dsl.domain

data class Skills(private val skills: List<Skill>) {
    val size = skills.size

    init {
        require(skills.isNotEmpty()) { DESCRIPTION_EMPTY_ERROR_MESSAGE }
    }
    companion object {
        private const val DESCRIPTION_EMPTY_ERROR_MESSAGE = "최소 1개 이상의 능력을 입력해주세요"
    }
}
