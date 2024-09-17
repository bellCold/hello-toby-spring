package tobyspring.hellospring

class Sort {
    fun sortByLength(list: List<String>): List<String> {
        return list.sortedBy { obj -> obj.length }
    }
}