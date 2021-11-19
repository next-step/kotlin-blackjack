package blackjack.domain.player

data class Profile(val name: Name, val status: PlayerStatus = PlayerStatus.STOP) {

    fun turnOff(): Profile {
        return Profile(name, PlayerStatus.STOP)
    }

    fun turnOn(): Profile {
        return Profile(name, PlayerStatus.BURST)
    }

    fun isBurst(): Boolean {
        return status == PlayerStatus.BURST
    }

    companion object {
        fun from(name: Name): Profile {
            return Profile(name)
        }
    }
}
