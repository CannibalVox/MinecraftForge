package net.minecraftforge.client.renderer;

import java.util.Comparator;

public class TriComparator implements Comparator<Integer> {
    private float mPlayerX;
    private float mPlayerY;
    private float mPlayerZ;
    
    private int[] mBuffer;
    
    private int mLastVertBeginIndex;
    
    public TriComparator(int[] buffer, float playerX, float playerY, float playerZ, boolean convertToTris)
    {
        mBuffer = buffer;
        mPlayerX = playerX;
        mPlayerY = playerY;
        mPlayerZ = playerZ;
        mLastVertBeginIndex = (convertToTris)?40:24;
    }

    @Override
    public int compare(Integer left, Integer right) {
        float leftVert1X = Float.intBitsToFloat(mBuffer[left]) - mPlayerX;
        float leftVert1Y = Float.intBitsToFloat(mBuffer[left+1]) - mPlayerY;
        float leftVert1Z = Float.intBitsToFloat(mBuffer[left+2]) - mPlayerZ;
        
        float leftDist1 = leftVert1X * leftVert1X + leftVert1Y * leftVert1Y + leftVert1Z * leftVert1Z;        
        
        float leftVert2X = Float.intBitsToFloat(mBuffer[left+8]) - mPlayerX;
        float leftVert2Y = Float.intBitsToFloat(mBuffer[left+9]) - mPlayerY;
        float leftVert2Z = Float.intBitsToFloat(mBuffer[left+10]) - mPlayerZ;
        
        float leftDist2 = leftVert2X * leftVert2X + leftVert2Y * leftVert2Y + leftVert2Z * leftVert2Z;
        
        float leftVert3X = Float.intBitsToFloat(mBuffer[left+16]) - mPlayerX;
        float leftVert3Y = Float.intBitsToFloat(mBuffer[left+17]) - mPlayerY;
        float leftVert3Z = Float.intBitsToFloat(mBuffer[left+18]) - mPlayerZ;
        
        float leftDist3 = leftVert3X * leftVert3X + leftVert3Y * leftVert3Y + leftVert3Z * leftVert3Z;
        
        float leftVert4X = Float.intBitsToFloat(mBuffer[left+mLastVertBeginIndex]) - mPlayerX;
        float leftVert4Y = Float.intBitsToFloat(mBuffer[left+mLastVertBeginIndex+1]) - mPlayerY;
        float leftVert4Z = Float.intBitsToFloat(mBuffer[left+mLastVertBeginIndex+2]) - mPlayerZ;
        
        float leftDist4 = leftVert4X * leftVert4X + leftVert4Y * leftVert4Y + leftVert4Z * leftVert4Z;
        
        float rightVert1X = Float.intBitsToFloat(mBuffer[right]) - mPlayerX;
        float rightVert1Y = Float.intBitsToFloat(mBuffer[right+1]) - mPlayerY;
        float rightVert1Z = Float.intBitsToFloat(mBuffer[right+2]) - mPlayerZ;
        
        float rightDist1 = rightVert1X * rightVert1X + rightVert1Y * rightVert1Y + rightVert1Z * rightVert1Z;
        
        float rightVert2X = Float.intBitsToFloat(mBuffer[right+8]) - mPlayerX;
        float rightVert2Y = Float.intBitsToFloat(mBuffer[right+9]) - mPlayerY;
        float rightVert2Z = Float.intBitsToFloat(mBuffer[right+10]) - mPlayerZ;
        
        float rightDist2 = rightVert2X * rightVert2X + rightVert2Y * rightVert2Y + rightVert2Z * rightVert2Z;
        
        float rightVert3X = Float.intBitsToFloat(mBuffer[right+16]) - mPlayerX;
        float rightVert3Y = Float.intBitsToFloat(mBuffer[right+17]) - mPlayerY;
        float rightVert3Z = Float.intBitsToFloat(mBuffer[right+18]) - mPlayerZ;
        
        float rightDist3 = rightVert3X * rightVert3X + rightVert3Y * rightVert3Y + rightVert3Z * rightVert3Z;
        
        float rightVert4X = Float.intBitsToFloat(mBuffer[right+mLastVertBeginIndex]) - mPlayerX;
        float rightVert4Y = Float.intBitsToFloat(mBuffer[right+mLastVertBeginIndex+1]) - mPlayerY;
        float rightVert4Z = Float.intBitsToFloat(mBuffer[right+mLastVertBeginIndex+2]) - mPlayerZ;
        
        float rightDist4 =  rightVert4X * rightVert4X + rightVert4Y * rightVert4Y + rightVert4Z * rightVert4Z;
        
        float leftScore =  Math.min(Math.min(leftDist1, leftDist2), Math.min(leftDist3, leftDist4));
                
        float rightScore =  Math.min(Math.min(rightDist1, rightDist2), Math.min(rightDist3, rightDist4));               
        
        return Float.compare(rightScore, leftScore);
    }
}