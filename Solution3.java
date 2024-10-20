

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * Solution3 类用于求解最大的整除子集问题。
 * 给定一个由无重复正整数组成的集合 nums，返回其中最大的整除子集 answer。
 * 子集中的每一对元素 (answer[i], answer[j]) 都必须满足：
 * - answer[i] % answer[j] == 0，或 answer[j] % answer[i] == 0。
 */

//优点
//清晰的注释：代码中有详细的注释，解释了每个步骤的目的和逻辑，便于理解和维护。
//边界条件处理：在处理输入数组为空的情况时，程序返回一个空列表，避免了数组越界错误。
//动态规划：使用动态规划来求解问题，时间复杂度为 (O(n^2))，在可接受范围内。
//结果构建：通过倒推的方式构建结果集，确保了结果的正确性。
//改进建议
//空间优化：可以考虑进一步优化空间复杂度，例如使用路径压缩来记录每个元素的前驱节点，从而减少空间使用。
//代码简化：部分代码可以进一步简化，例如在更新 dp[i] 时，可以直接更新 maxSize 和 maxVal，减少一次遍历。
public class Solution3 {

    /**
     * @description:
     * 找到最大的整除子集，并以列表形式返回。
     *
     * @param nums 输入的整数数组
     * @return 最大的整除子集
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;

        // 检查输入是否为空，如果为空则返回空列表，避免数组越界错误
        if (len == 0) {
            return new ArrayList<>();
        }

        // 将数组按升序排序，确保可以顺序判断整除关系
        Arrays.sort(nums);

        // 动态规划数组 dp，其中 dp[i] 表示以 nums[i] 结尾的最大整除子集的大小
        int[] dp = new int[len];
        Arrays.fill(dp, 1); // 初始化 dp 数组，所有元素初始值为 1

        // 用于记录最大整除子集的大小和其中的最大元素
        int maxSize = 1;
        int maxVal = nums[0];

        // 遍历数组，计算每个位置的最大整除子集的大小
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // 如果 nums[i] 可以被 nums[j] 整除，则更新 dp[i]
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            // 更新最大整除子集的大小和最后一个元素的值
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }

        // 用于存储最终的最大整除子集
        List<Integer> res = new ArrayList<>();

        // 从数组末尾开始倒推，找出最大整除子集的元素
        for (int i = len - 1; i >= 0; i--) {
            // 如果当前元素符合条件，则加入结果集，并更新 maxVal 和 maxSize
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                res.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }

        return res;
    }
}