package yampi.msh.abohava.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}