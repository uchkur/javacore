class BugOverride
{
    private String a1;
    BugOverride (String a1, int a2, int a3, int a4)
    {
        this.a1=a1;
    }
}

Integer i1 = null;
int i2 = i1;

System.out.println("Java version is: " + System.getProperty("java.version"));
def bug= new BugOverride("Ser", i2, 1, 1);
