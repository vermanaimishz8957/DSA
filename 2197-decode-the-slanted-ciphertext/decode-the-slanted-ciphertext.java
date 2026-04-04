class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 0) return "";

        int cols = encodedText.length() / rows;
        char[][] mat = new char[rows][cols];

        int i = 0, j = 0;

        // Fill matrix row-wise
        for (int ind = 0; ind < encodedText.length(); ind++) {
            mat[i][j] = encodedText.charAt(ind);
            j++;
            if (j == cols) {
                j = 0;
                i++;
            }
        }

        StringBuilder sb = new StringBuilder();

        // Traverse diagonally
        for (j = 0; j < cols; j++) {
            int row = 0, col = j;

            while (row < rows && col < cols) {
                sb.append(mat[row][col]);
                row++;
                col++;
            }
        }

        return sb.toString().stripTrailing();
    }
}