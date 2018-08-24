public final class Diamond {

    private Diamond() {}

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Diamond <[A-Z]>");
            return;
        }
        Diamond.draw(args[0].toUpperCase().charAt(0));
    }

    public static void draw(char c) {
        if (c < 'A' || c > 'Z') {
            throw new RuntimeException("Required character in range [A-Z]!");
        }
        int max = c - 'A';
        for (int row = 0; row <= max; row++) {
            drawSection(row, max);
        }
        for (int row = max - 1; row >= 0; row--) {
            drawSection(row, max);
        }
    }

    private static void drawSection(int row, int max) {
        for (int n = max - row; n > 0; n--) {
            System.out.print(" ");
        }
        for (int col = 0; col < row * 2 + 1; col++) {
            if (col == 0 || col == row * 2) {
                System.out.print((char)(row + 'A'));
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
