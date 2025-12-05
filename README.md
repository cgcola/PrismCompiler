<div align="center">

  <h1>Prism Compiler</h1>

  <p>
    <strong>A visually immersive Compiler Simulator built with JavaFX.</strong>
  </p>

  <p>
    Prism visualizes the <strong>Lexical</strong>, <strong>Syntax</strong>, and <strong>Semantic</strong> analysis phases of compilation in real-time, wrapped in a futuristic Cyberpunk/Neon aesthetic.
  </p>

  <p>
    <a href="https://www.java.com">
      <img src="https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java 17+">
    </a>
    <a href="https://openjfx.io/">
      <img src="https://img.shields.io/badge/JavaFX-UI-4285F4?style=for-the-badge&logo=openjdk&logoColor=white" alt="JavaFX">
    </a>
    <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="License">
  </p>

</div>

<hr />

<h2>Key Features</h2>

<h3>Interactive UI</h3>
<ul>
  <li><strong>Visual Pipeline:</strong> Real-time "Traffic Light" status indicators for every analysis phase.</li>
  <li><strong>Neon Aesthetic:</strong> Custom CSS styling with a toggleable <strong>Dark (Cyberpunk)</strong> and <strong>Light</strong> mode.</li>
  <li><strong>File Support:</strong> Native loading of <code>.txt</code> and <code>.java</code> source files.</li>
</ul>

<h3>⚙️ Compiler Engine</h3>
<table>
  <thead>
    <tr>
      <th>Phase</th>
      <th>Function</th>
      <th>Key Mechanics</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>1. Lexical</strong></td>
      <td>Tokenizer</td>
      <td>Uses Regex to break code into tokens: <code>&lt;type&gt;</code>, <code>&lt;id&gt;</code>, <code>&lt;val&gt;</code>.</td>
    </tr>
    <tr>
      <td><strong>2. Syntax</strong></td>
      <td>Parser</td>
      <td>Validates grammar rules (e.g., ensuring <code>;</code> exists, checking declaration structure).</td>
    </tr>
    <tr>
      <td><strong>3. Semantic</strong></td>
      <td>Logic</td>
      <td>Manages <strong>Symbol Table</strong>, enforces <strong>Scope</strong>, and validates <strong>Type Compatibility</strong>.</td>
    </tr>
  </tbody>
</table>

<hr />

<h2>Supported Syntax</h2>
<p>Prism supports a strict subset of the Java language.</p>

<h3>Supported Data Types</h3>
<p>
  <code>byte</code>, <code>short</code>, <code>int</code>, <code>long</code>, 
  <code>float</code>, <code>double</code>, 
  <code>char</code>, <code>String</code>, <code>boolean</code>
</p>

<h3>Example Code</h3>
<pre lang="java">
// 1. Declaration
int count;
String username;

// 2. Initialization
double price = 99.99;
boolean isActive = true;

// 3. Logic & Assignment
count = 50;
</pre>

<hr />

<h2>🛠️ Architecture</h2>
<p>The project follows the <strong>MVC (Model-View-Controller)</strong> pattern:</p>
<ul>
  <li><strong>Controller (<code>com.prismx.controller</code>):</strong> Orchestrates the UI flow and analysis sequence.</li>
  <li><strong>Model (<code>com.prismx.model</code>):</strong> Contains the core compiler logic (Lexer, Parser, Semantic Analyzer).</li>
  <li><strong>View (<code>com.prismx.view</code>):</strong> FXML layouts and CSS stylesheets.</li>
</ul>

<hr />

<h2>Setup & Run</h2>
<ol>
  <li>Clone the repository.</li>
  <li>Open in <strong>IntelliJ IDEA</strong>.</li>
  <li>Add <strong>JavaFX SDK</strong> to your project libraries.</li>
  <li>Run <code>prismView.java</code>.</li>
</ol>

<div align="center">
  <sub>Developed with using JavaFX</sub>
</div>
