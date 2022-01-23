# Prerequisites
## 1. Java Installation
      - Make sure Java 11 version is installed and Path is set to bin folder
## 2. Maven Installation
      - Make sure Maven software is installed and Path is set to bin folder
      
      
# Steps to Run from command line
     - Clone the repo :  git clone https://github.com/deeptiraju24/demo.git
     
     - Switch to demo project: cd demo
     
     - Run the tests: mvn test
     
     
## What steps test script perform?
    - Launches http://www.youtube.com
    
    - Verify the YouTube logo is present on top of the page
    
    - Click Apps icon and verify the list of application names
    
    - Search for 'bata drums' and scroll to the last displayed video
      (here the auto pagination happens and the new videos loaded automatically)
    
    - To confirm the auto pagination happens, 
      the script get the total videos count before and after the scroll operation and 
      confirm if more videos are loaded for every scroll till all videos are loaded.
