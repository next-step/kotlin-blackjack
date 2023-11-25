package player

class Player() {

    private var _status = Status.PLAYING
    val status: Status
        get() = _status

    fun hitDone() {
        _status = Status.STAND
    }
}
