package step1.study.builder

/**
 * 기술 객체 빌더
 * */
class SkillBuilder {

    private val skills: MutableList<Pair<String, String>> = mutableListOf()

    fun soft(description: String) {
        skills.add(SKILL_LEVEL_SOFT_KEY to description)
    }

    fun hard(description: String) {
        skills.add(SKILL_LEVEL_HARD_KEY to description)
    }

    fun build() = skills

    companion object {
        private const val SKILL_LEVEL_SOFT_KEY = "SKILL_LEVEL_SOFT_KEY"
        private const val SKILL_LEVEL_HARD_KEY = "SKILL_LEVEL_HARD_KEY"
    }

}