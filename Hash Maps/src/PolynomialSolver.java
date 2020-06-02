import java.util.*;

public class PolynomialSolver {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Polynomial polynomial = new Polynomial();
        Polynomial other = new Polynomial();
        other.setCoefficient(0, 1);
        other.setCoefficient(1, 3);
        int choice;
        do {
            System.out.println("1) Set Coefficient ");
            System.out.println("2) Add");
            System.out.println("3) Subtract");
            System.out.println("4) Multiply");
            System.out.println("5) Print");
                choice = in.nextInt();

            switch (choice){
                case 1 :
                    int degree = in.nextInt();
                    int coefficient = in.nextInt();
                    polynomial.setCoefficient(degree, coefficient);
                    break;
                case 2 :
                    Polynomial ans = polynomial.add(other);
                    ans.print();
                    break;
                case 3 :
                    ans = polynomial.subtract(other);
                    ans.print();
                    break;
                case 4 :
                    ans = polynomial.multiply(other);
                    ans.print();
                    break;
                case 5 :
                    polynomial.print();
                    break;
            }
        } while (choice <= 5);
    }
}

class Polynomial{
    private HashMap<Integer, Integer> degreeCoefficientMap;
    private int degree;
    private int terms;

    Polynomial(){
        degreeCoefficientMap = new HashMap<>();
        degree = 0;
        terms = 0;
    }
    Polynomial(HashMap<Integer, Integer> degreeCoefficientMap, int degree, int terms){
        this.degreeCoefficientMap = degreeCoefficientMap;
        this.terms = terms;
        this.degree = degree;
    }

    public void setCoefficient(int degree, int coefficient){
        if(degreeCoefficientMap.containsKey(degree))
            degreeCoefficientMap.put(degree, coefficient);
        else {
            degreeCoefficientMap.put(degree, coefficient);
            this.degree = this.degree > degree ? this.degree : degree;
            terms++;
        }
    }

    public void print(){
        ArrayList<Integer> degreeList = getdegreeList(degreeCoefficientMap);
        Collections.sort(degreeList);
        for(Integer degree : degreeList){
            print(degreeCoefficientMap.get(degree), degree);
            System.out.print(" ");
        }
    }
    private void print(int coefficient, int degree){
        System.out.print(coefficient + "x" + degree);
    }
    private ArrayList<Integer> getdegreeList(HashMap<Integer, Integer> hashMap){
        ArrayList<Integer> ans = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : hashMap.entrySet()){
            ans.add(entry.getKey());
        }
        return ans;
    }

    public Polynomial add(Polynomial other){
        HashMap<Integer, Integer> degreeCoefficientFrequencyMap = new HashMap<>();
        degreeCoefficientFrequencyMap.putAll(degreeCoefficientMap);
        int degree = this.degree, terms = other.terms;

        for(Map.Entry<Integer, Integer> entry : other.getDegreeCoefficientMap().entrySet()){
            if(!degreeCoefficientFrequencyMap.containsKey(entry.getKey())) terms++;
            degreeCoefficientFrequencyMap.put(entry.getKey(),
                    degreeCoefficientFrequencyMap.getOrDefault(entry.getKey(), 0) + entry.getValue());
            degree = Math.max(degree, entry.getKey());
        }
        return new Polynomial(degreeCoefficientFrequencyMap, degree, terms);
    }

    public Polynomial subtract(Polynomial other){
        HashMap<Integer, Integer> degreeCoefficientFrequencyMap = new HashMap<>();
        int degree = other.getDegree();
        int terms = degreeCoefficientMap.size();
        degreeCoefficientFrequencyMap.putAll(degreeCoefficientMap);

        for(Map.Entry<Integer, Integer> entry : other.getDegreeCoefficientMap().entrySet()){
            degreeCoefficientFrequencyMap.put(entry.getKey(),
                    degreeCoefficientFrequencyMap.getOrDefault(entry.getKey(), 0) - entry.getValue());
            if(degreeCoefficientFrequencyMap.get(entry.getKey()) == 0) {
                degreeCoefficientFrequencyMap.remove(entry.getKey());
                terms--;
            }
        }

        for(Map.Entry<Integer, Integer> entry : degreeCoefficientFrequencyMap.entrySet()){
            degree = Math.max(degree, entry.getKey());
        }

        return new Polynomial(degreeCoefficientFrequencyMap, degree, terms);
    }

    public Polynomial multiply(Polynomial other){
        HashMap<Integer, Integer> degreeCoefficientFrequencyMap = new HashMap<>();
        int degree = other.getDegree() + this.degree;
        int terms = 0;

        for(Map.Entry<Integer, Integer> entry : other.getDegreeCoefficientMap().entrySet()){

            for(Map.Entry<Integer, Integer> mapEntry : degreeCoefficientMap.entrySet()){
                if(!degreeCoefficientFrequencyMap.containsKey(entry.getKey() + mapEntry.getKey()))
                    terms++;
                degreeCoefficientFrequencyMap.put(entry.getKey() + mapEntry.getKey(),
                        degreeCoefficientFrequencyMap.getOrDefault(mapEntry.getKey()+entry.getValue(), 0)
                                +  mapEntry.getValue()*entry.getValue());
            }
        }

        System.out.println(terms);
        return new Polynomial(degreeCoefficientFrequencyMap, degree, terms);
    }

    public HashMap<Integer, Integer> getDegreeCoefficientMap() {
        return degreeCoefficientMap;
    }
    public int getDegree() {
        return degree;
    }
}