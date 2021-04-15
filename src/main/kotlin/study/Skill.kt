package study

class Skill(val name: String) {
    override fun equals(other: Any?): Boolean {
        if (other is Skill) {
            return this.name == other.name
        }
        return super.equals(other)
    }
}
