package study

class Skills(val skills: List<Skill>) {

    fun contains(value: Skill): Boolean {
        for (skill in skills) {
            if (skill == value) {
                return true
            }
        }
        return false
    }
}
