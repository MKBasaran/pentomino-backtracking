# Pentomino Solver – Recursive Backtracking Algorithm  

## 🚀 Overview  
This project implements a **highly optimized recursive backtracking algorithm** to solve the **Pentomino tiling problem**. The program efficiently determines whether a given set of **12 unique pentomino pieces** can fully cover a **specified grid size**, optimizing placement by **eliminating duplicates, avoiding disconnected spaces, and reducing unnecessary computations**.  

## 🛠 Technologies Used  
- **Java** – Primary language for implementation  
- **Java Swing** – Graphical user interface for visualization  
- **Recursive Backtracking** – Algorithm for exploring valid tile placements  
- **Pentomino Database** – Precomputed transformations for efficient piece placement  

## 📂 How to Run the Project  
1. **Clone the repository**  
git clone https://github.com/MKBasaran/pentomino-backtracking.git 
cd pentomino-solver
2. **Compile and run the program**  
javac Search.java 
java Search


## 🔍 Key Features  
- ✅ **Optimized Recursive Search:** Uses **pruning and connectivity checks** to drastically reduce computation time.  
- ✅ **Dynamic Grid Resizing:** Users can **modify the board size** by adjusting `horizontalGridSize` and `verticalGridSize`.  
- ✅ **Automatic Solution Display:** The solver runs automatically and fills the board within **milliseconds** for most cases.  
- ✅ **Multiple Solutions:** Running the solver multiple times may result in different valid placements.  

## 📊 Performance Optimization  
Originally, a brute-force approach required **16 minutes** to compute solutions. By implementing:  
- **CheckConnectivity()** – Prevents invalid placements, reducing unnecessary attempts.  
- **Backtracking with Memoization** – Stores previous configurations, cutting runtime to **milliseconds**.  

## 👨‍💻 Contributors  
- **Kaan Başaran**  
- **Nikita Souslau**  
- **Vasil Raychev**  
- **Luca Nichifor**  
- **Théo Massignan**  
- **Matteo Cannata**  
- **Kennedy Fernandes**  

## 📝 Future Improvements  
- 🔹 **Parallelization with Multi-threading** – To further speed up large grid computations.  
- 🔹 **More Efficient Heuristic Search** – Evaluating piece placement strategies for **faster convergence**.  
- 🔹 **Extended Grid Support** – Implementing **non-rectangular grids and variable constraints**.  

