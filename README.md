# plylib

A Java library to parse meshes in the Ply File Format.

To use the library in your program, create a class which implements the 
PlyHandler interface. Use the PlyReader.parse(..., handler) methods to start
reading a Ply file. The PlyReader will make the appropriate calls to the 
handler during the parsing of the file.

The PlyEchoHandler is an example implementation of a PlyHandler which prints
the contents of a PlyFile back to the standard output stream in ASCII format.
