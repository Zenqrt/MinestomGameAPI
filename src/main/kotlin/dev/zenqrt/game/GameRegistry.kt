package dev.zenqrt.game

class GameRegistry : ArrayList<Game>() {

    override fun add(index: Int, element: Game) {
//        element.onRegister()
        super.add(index, element)
    }

    override fun set(index: Int, element: Game): Game {
//        element.onRegister()
        return super.set(index, element)
    }


}