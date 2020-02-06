
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class AllPermutation 
{ 

	private final String Arr[]; 

	private int Indexes[]; 


    // The index of the first "increase" 
    // in the Index array which is the smallest 
    // i such that Indexes[i] < Indexes[i + 1] 
	private int Increase; 


	public AllPermutation(String arr[]) 
	{ 
		this.Arr = arr; 
		this.Increase = -1; 
		this.Indexes = new int[this.Arr.length]; 
	} 

   // Initialize and output 
    // the first permutation 
	public void GetFirst() 
	{ 

	
        // Initialize the values in Index array 
        // from 0 to n - 1 
		this.Indexes = new int[this.Arr.length]; 

	
		for (int i = 0; i < Indexes.length; ++i) 
		{ 
			this.Indexes[i] = i; 
		} 

		
		this.Increase = 0; 

		this.Output(); 
	} 

	public boolean HasNext() 
	{ 

		
		return this.Increase != (this.Indexes.length - 1); 
	} 

	
	public void GetNext() 
	{ 

		
		if (this.Increase == 0) 
		{ 

			
			this.Swap(this.Increase, this.Increase + 1); 

			
			this.Increase += 1; 
			while (this.Increase < this.Indexes.length - 1
				&& this.Indexes[this.Increase] 
						> this.Indexes[this.Increase + 1]) 
			{ 
				++this.Increase; 
			} 
		} 
		else
		{ 
 // Value at Indexes[Increase + 1] is greater than Indexes[0] 
            // no need for binary search, 
            // just swap Indexes[Increase + 1] and Indexes[0] 
			if (this.Indexes[this.Increase + 1] > this.Indexes[0]) 
			{ 
				this.Swap(this.Increase + 1, 0); 
			} 
			else
			{ 

		// Binary search
				int start = 0; 
				int end = this.Increase; 
				int mid = (start + end) / 2; 
				int tVal = this.Indexes[this.Increase + 1]; 
				while (!(this.Indexes[mid]<tVal&& this.Indexes[mid - 1]> tVal)) 
				{ 
					if (this.Indexes[mid] < tVal) 
					{ 
						end = mid - 1; 
					} 
					else
					{ 
						start = mid + 1; 
					} 
					mid = (start + end) / 2; 
				} 

				
				this.Swap(this.Increase + 1, mid); 
			} 

			for (int i = 0; i <= this.Increase / 2; ++i) 
			{ 
				this.Swap(i, this.Increase - i); 
			} 

			this.Increase = 0; 
		} 
		this.Output(); 
	} 

 // Function to output the input array 
	private void Output() 
	{ 
		for (int i = 0; i < this.Indexes.length; ++i) 
		{ 

			
			System.out.print(this.Arr[this.Indexes[i]]); 
			System.out.print(" "); 
		} 
		System.out.println(); 
	} 


	private void Swap(int p, int q) 
	{ 
		int tmp = this.Indexes[p]; 
		this.Indexes[p] = this.Indexes[q]; 
		this.Indexes[q] = tmp; 
	} 
} 





class AllPermutations
{ 
	public static void main(String args[]) 
	{ 
		//csv file
		String csvFile = "./input.csv";
        String line = "";
        String cvsSplitBy = ",";
//BufferReader object made
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	//ReadLine from csv file
            while ((line = br.readLine()) != null) {

               //Split by Line
                String[] arr = line.split(cvsSplitBy);
                //Store the input in an array
                AllPermutation perm = new AllPermutation(arr); 
		perm.GetFirst(); 
		while (perm.HasNext()) 
		{ 
			perm.GetNext(); 
		} 
               

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

       
	} 
} 
