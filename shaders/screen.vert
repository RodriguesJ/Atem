varying vec3 vertColor;

void main(){
    gl_Position = ftransform();
    vertColor = gl_Color.rgb;
}