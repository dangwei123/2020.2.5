class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum=0;
        for(int num:nums){
            sum+=num;
        }
        if(sum%k!=0){
            return false;
        }
        sum/=k;
        Arrays.sort(nums);
        int[] bucket=new int[k];
        Arrays.fill(bucket,sum);
        return canPartitionKSubsets(bucket,nums,k,nums.length-1);
    }
    private boolean canPartitionKSubsets(int[] bucket,int[] nums,int k,int cur){
        if(cur<0){
            return true;
        }
        for(int i=0;i<k;i++){
            if(bucket[i]==nums[cur]||bucket[i]-nums[cur]>=nums[0]){
                bucket[i]-=nums[cur];
                if(canPartitionKSubsets(bucket,nums,k,cur-1)){
                    return true;
                }
                bucket[i]+=nums[cur];
            }
        }
        return false;
    }
}


class Solution {
    public int findLength(int[] A, int[] B) {
        int[][] dp=new int[A.length+1][B.length+1];
        int res=0;
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                if(A[i]==B[j]){
                    dp[i+1][j+1]=dp[i][j]+1;
                    res=Math.max(dp[i+1][j+1],res);
                }else{
                    dp[i+1][j+1]=0;
                }
            }
        }
        return res;
    }
}

class Solution {
    public int minSwap(int[] A, int[] B) {
        int[][] dp=new int[2][A.length];
        dp[0][0]=0;
        dp[1][0]=1;
        for(int i=1;i<A.length;i++){
            dp[0][i]=Integer.MAX_VALUE;
            dp[1][i]=Integer.MAX_VALUE;
            if(A[i]>A[i-1]&&B[i]>B[i-1]){
                dp[0][i]=Math.min(dp[0][i-1],dp[0][i]);
                dp[1][i]=Math.min(dp[1][i-1]+1,dp[1][i]);
            }
            if(A[i]>B[i-1]&&B[i]>A[i-1]){
                dp[0][i]=Math.min(dp[1][i-1],dp[0][i]);
                dp[1][i]=Math.min(dp[0][i-1]+1,dp[1][i]);
            }
        }
        return Math.min(dp[0][A.length-1],dp[1][A.length-1]);
    }
}

class Solution {
    public int minSwap(int[] A, int[] B) {
        int n1=0;
        int s1=1;
        for(int i=1;i<A.length;i++){
            int n2=Integer.MAX_VALUE;
            int s2=Integer.MAX_VALUE;
            if(A[i]>A[i-1]&&B[i]>B[i-1]){
                n2=Math.min(n2,n1);
                s2=Math.min(s2,s1+1);
            }
            if(A[i]>B[i-1]&&B[i]>A[i-1]){
                n2=Math.min(n2,s1);
                s2=Math.min(s2,n1+1);
            }
            s1=s2;
            n1=n2;
        }
        return Math.min(s1,n1);
    }
}

class Solution {
    public double soupServings(int N) {
        if(N>4800){
            return 1.0;
        }
        N=N/25+(N%25==0?0:1);
        double[][] dp=new double[N+1][N+1];
        for(int i=0;i<=N;i++){
                dp[0][i]=1;
                dp[i][0]=0;
        }
        dp[0][0]=0.5;
        for(int i=1;i<=N;i++){
            int a1=i-4>0?i-4:0;
            int a2=i-3>0?i-3:0;
            int a3=i-2>0?i-2:0;
            int a4=i-1>0?i-1:0;
            for(int j=1;j<=N;j++){
                int b1=j;
                int b2=j-1>0?j-1:0;
                int b3=j-2>0?j-2:0;
                int b4=j-3>0?j-3:0;
                dp[i][j]=0.25*(dp[a1][b1]+dp[a2][b2]+dp[a3][b3]+dp[a4][b4]);
            }
        }
        return dp[N][N];
    }
}

class Solution {
    public int lenLongestFibSubseq(int[] A) {
        int len=A.length;
        int[][] dp=new int[len][len];
        int res=0;
        for(int i=2;i<len;i++){
            int left=0;
            int right=i-1;
            while(left<right){
                int sum=A[left]+A[right];
                if(sum==A[i]){
                    dp[right][i]=dp[left][right]+1;
                    res=Math.max(res,dp[right][i]);
                    left++;
                    right--;
                }else if(sum<A[i]){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return res==0?0:res+2;
    }
}

class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum=0;
        for(int stone:stones){
            sum+=stone;
        }
        int tmp=sum;
        sum/=2;
        int[] dp=new int[sum+1];
        for(int i=0;i<stones.length;i++){
            for(int j=sum;j>=stones[i];j--){
                dp[j]=Math.max(dp[j-stones[i]]+stones[i],dp[j]);
            }
        }
        return tmp-2*dp[sum];
    }
}

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int a=text1.length();
        int b=text2.length();
        int[][] dp=new int[a+1][b+1];
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                if(text1.charAt(i)==text2.charAt(j)){
                    dp[i+1][j+1]=dp[i][j]+1;
                }else{
                    dp[i+1][j+1]=Math.max(dp[i][j+1],dp[i+1][j]);
                }
            }
        }
        return dp[a][b];
    }
}

class Solution {
    public int maxSumDivThree(int[] nums) {
        int len=nums.length;
        int[] dp=new int[3];
        for(int i=0;i<len;i++){
            int mod=nums[i]%3;
            int a=dp[(3+0-mod)%3];
            int b=dp[(3+1-mod)%3];
            int c=dp[(3+2-mod)%3];
            if(a!=0||mod==0) dp[0]=Math.max(dp[0],a+nums[i]);
            if(b!=0||mod==1) dp[1]=Math.max(dp[1],b+nums[i]);
            if(c!=0||mod==2) dp[2]=Math.max(dp[2],c+nums[i]);
        }
        return dp[0];
    }
}

