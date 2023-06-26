package blackjack.domain.player

enum class GamerState {
    WAIT {
        override fun canHit(): Boolean {
            return true
        }

        override fun canToStay(): Boolean {
            return true
        }
    },
    STAY {
        override fun canHit(): Boolean {
            return false
        }

        override fun canToStay(): Boolean {
            return false
        }
    },
    BUST {
        override fun canHit(): Boolean {
            return false
        }

        override fun canToStay(): Boolean {
            return false
        }
    },
    ;

    abstract fun canHit(): Boolean

    abstract fun canToStay(): Boolean
}
