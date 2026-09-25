public class A3_BackyardToolshedRoutine {

    // Note: the brief says CuttingTool's override should also "call super.use() first," but GardenTool's
    // use() is abstract (no body to call via super) - so CuttingTool builds its own base message directly,
    // and only Pruner (whose parent, CuttingTool, has a real implementation) actually calls super.use().
    static abstract class GardenTool {
        public abstract String use();
    }

    static class CuttingTool extends GardenTool {
        public CuttingTool() {
        }

        @Override
        public String use() {
            return "Using the tool in the garden, blade sharpened first";
        }
    }

    static class Pruner extends CuttingTool {
        public Pruner() {
        }

        @Override
        public String use() {
            return super.use() + ", then trimming branches precisely";
        }
    }

    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        System.out.println(c.use());

        Pruner p = new Pruner();
        System.out.println(p.use());
    }
}
