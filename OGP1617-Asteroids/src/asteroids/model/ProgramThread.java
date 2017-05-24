package asteroids.model;

public class ProgramThread extends Thread {
    public ProgramThread(Program program){
        this.program = program;
    }

    public Program getProgram() {
        return this.program;
    }

    private final Program program;

    @Override
    public void run() {
        this.getProgram().getMain().evaluate(this.getProgram());
    }
}
