package com.hmtmcse.sample


class IteratorTest {

    public static void main(String[] args) {
//        def list1 = [1, 2, 3, 4]
//        Iterator i = list1.iterator();
//        while (i.hasNext()) {
//            def n = i.next();
//            println(n)
//            if (n == 2) i.remove();
//        }
//        println(list1)

        List<DataX> data = getData()
        println("--------------- Main ----------------")
        List<DataX> result = process(data)

        println("--------------- Processed ----------------")
        process(result)

    }

    static List<DataX> process(List<DataX> lists){
        Iterator iterator = lists.iterator();
        while (iterator.hasNext()){
            DataX dataX = iterator.next()
            println(dataX.name + " " + dataX.age)
            if (dataX.child){
//                dataX.child = process(dataX.child)
                process(dataX.child)
            }
            if (dataX.name.equals("Remove")){
                iterator.remove()
            }else if (dataX.name.equals("Modify")){
                dataX.name = "Modify Done"
            }
        }
        return lists
    }

    static List<DataX> getData(){
        List<DataX> lists = []
        lists.add(new DataX("Nijum", "20"))
        lists.add(new DataX("Sammi", "21"))
        lists.add(new DataX("Remove", "00"))
        lists.add(new DataX("Modify", "00"))
        DataX child = new DataX("Mia", "23")
        child.child.add(new DataX("Touhid", "24"))
        child.child.add(new DataX("Hossan", "23"))
        child.child.add(new DataX("Remove", "00"))
        child.child.add(new DataX("Modify", "00"))
        lists.add(child)

        child = new DataX("Abu", "30")
        child.child.add(new DataX("Babu", "31"))
        child.child.add(new DataX("Tanvir", "18"))
        child.child.add(new DataX("Remove", "00"))
        child.child.add(new DataX("Modify", "00"))
        lists.add(child)

        return lists
    }
}
