package model

class Player(name: PlayerName): AbstractPlayer(name, PlayerType.PLAYER) {
    constructor(name: String): this(PlayerName(name))
}
