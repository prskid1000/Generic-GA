public class gnetc
    {
        byte gn[][];
        double f[],ft=-1,zp=0;
        int  n=2986,g=0,gl=0;
        String ev[],en[],sln[];
        //constructor
        gnetc(int g,String ev[])     
            {
                sln=new String[231];
                this.ev=new String[ev.length];
                this.ev=ev;
                while(true)
                    {
                        //finding gene length
                        if(Math.pow(2,gl)>ev.length)break;
                        gl++;
                    }
                g=g*gl;
                this.g=g;
                this.encd();
                gn=new byte[n][g];
                f=new double[n];
                for(int  i=0;i<n;i++)
                    {
                        for(int  j=0;j<g;j++)
                            {
                                gn[i][j]=0; 
                            }
                        f[i]=0;
                    }
            }
        //crossover
        public void crsover()
            {
                for(int i=1;i<n;i++)
                    {
                        if(Math.random()>Math.random()||Math.random()<Math.random())
                            {
                                int p1=(int )(Math.random()*(g-1));
                                int p2=(int )(Math.random()*(g-1));

                                for(int j=p1;j<p2;j++)                        
                                    {           
                                        byte t=gn[i][j];
                                        gn[i][j]=gn[i-1][j];
                                        gn[i-1][j]=t;
                                    }

                            }
                    }
            }
        //mutation
        public void mutn()
            {
                for(int i=0;i<n;i++)
                    {
                        if((Math.random()>Math.random()||Math.random()<Math.random()))
                            {
                                int  p=(int )(Math.random()*(g-1));
                                if(gn[i][p]==0)gn[i][p]=1;
                                else gn[i][p]=0;
                            }
                    }          
            } 
        //selection
        public void sct()
            {

                for(int i=0;i<n;i++)
                    {
                        if((f[i])<ft)
                            {
                                f[i]=0;
                            }
                    }
            }
        // create population
        public void rpdt()
            {
                for(int i=0;i<n;i++)
                    {
                        if(f[i]==0)
                            {
                                for(int j=0;j<g;j++)
                                    {
                                        if(Math.random()>Math.random())
                                            {
                                                gn[i][j]=1;

                                            }
                                        else
                                            {
                                                gn[i][j]=0;

                                            }
                                    }        
                            }
                    }

            }
        //data encoder
        public void encd()
            {               
                en=new String[ev.length];
                for(int i=0;i<en.length;i++)
                    {
                        en[i]="";
                    }
                for( int i=0;i<ev.length;i++)
                    {
                        long  z=i;
                        for(int j=0;j<gl;j++)
                            {
                                //creating encoding value
                                if(z%2==1)en[i]="1"+en[i];
                                else en[i]="0"+en[i];
                                z=z/2;
                            }
                    }

            }
        //data decoder
        public String decd(byte b[])
            {
                String dv="",t="";
                long  k=0;
                for(int j=0;j<=b.length;j++)
                    {                                 
                        if( k==gl)
                            {
                                for(int i=0;i<en.length;i++)
                                    {    
                                        if(t.equalsIgnoreCase(en[i])) 
                                            { 
                                                dv=dv+ev[i];
                                                break;
                                            }
                                    }
                                t="";
                                if(j!=b.length)t=t+Long.toString(b[j]);                                                          
                                k=1;
                            }
                        else 
                            {
                                t=t+Long.toString(b[j]);
                                k++;
                            }
                    }
                return dv;  
            }
        //fitness assigner
        public void elvt()
            {
                String ans[]=new String[n];
                for(int i=0;i<n;i++)
                    {
                        byte b[]=new byte[g];
                        for(int j=0;j<g;j++)
                            {
                                b[j]=gn[i][j];
                            }
                        ans[i]=this.decd(b); 
                    }
                //calling fitness evaluator
                for(int j=0;j<n;j++)
                    {
                        f[j]=this.fts(ans[j]);
                    }
                //average fitness
                ft=0;
                for(int i=0;i<n;i++)
                    {
                        ft=ft+f[i];
                    }
                ft=ft/n;  
                //answer storer
                for(int i=0;i<n;i++)
                    {
                        if(f[i]==1)
                            {
                                for(int j=0;j<231;j++)
                                    {                                    
                                        if(sln[j]!=null&&sln[j].equalsIgnoreCase(ans[i]))
                                            {                                               
                                                break;
                                            }
                                        else if(sln[j]==null)
                                            {
                                                sln[j]=ans[i];
                                                break;
                                            }
                                    }
                            }
                    }
            }
        //evolution
        public void evlv()
            {
                for(int i=0;i<Math.random()*231;i++)
                    {
                        this.rpdt();
                        this.crsover();
                        this.mutn();
                        this.elvt();
                        this.sct(); 
                    }
                this.rps();
            }
        //  fitness calculator
        public double fts(String an)
            {
                double v=0;
                double m=0;
                String  r[]=new String[an.length()];
                for(int i=0;i<an.length();i++)
                    {
                        r[i]=""+an.charAt(i);

                    }
                char w[]={'/','*','-','+'};
                for(int i=0;i<4;i++)
                    {
                        for(int j=1;j<an.length()-1;j++)
                            {
                                if(r[j].charAt(0)==w[i])
                                    {

                                        int k=j-1 ;
                                        boolean b1=false,b2=false;
                                        String s1="",s2="";
                                        while(k>=0)
                                            {
                                                double y=(r[k]).charAt(0);

                                                if((y<48||y>57)&&y!=32)break;

                                                else if(b1==true&&y==32)break;

                                                else if(y>=48&&y<=57)
                                                    {

                                                        b1=true;

                                                        s1=r[k]+s1;
                                                        r[k]=" ";
                                                        int q=0;
                                                        if(k-1>=0) q=r[k-1].charAt(0);
                                                        if(q>=48&&q<=57)
                                                        {
                                                            }
                                                        else
                                                            {
                                                             break;
                                                            }
                                                    }
                                                k--;  
                                            }
                                        k=j+1;
                                        while(k<an.length())
                                            {
                                                double y=(r[k]).charAt(0);

                                                if((y<48||y>57)&&y!=32)break;
                                                else if(b2==true&&y==32)break;
                                                else if(y>=48&&y<=57)
                                                    {
                                                        b2=true;
                                                        s2=r[k]+s2;
                                                        r[k]=" ";
                                                        int q=0;
                                                        if(k+1<an.length())q=r[k+1].charAt(0);
                                                        if(q>=48&&q<=57)
                                                        {                                                           
                                                        }
                                                        else
                                                        {
                                                             break;
                                                        }
                                                    }
                                                k++;
                                            }
                                        if(b1==true&&b2==true)
                                            {
                                                switch(w[i])
                                                    {
                                                        case '/':
                                                                {

                                                                    m=Double.parseDouble(s1)/Double.parseDouble(s2);
                                                                    r[j]=Double.toString(m);                                                                                                               
                                                                }
                                                            break;
                                                        case '*':
                                                                {

                                                                    m=Double.parseDouble(s1)*Double.parseDouble(s2);
                                                                    r[j]=Double.toString(m);                               
                                                                }
                                                            break;
                                                        case '+':
                                                                {

                                                                    m=Double.parseDouble(s1)+Double.parseDouble(s2);
                                                                    r[j]=Double.toString(m);                                                        
                                                                }
                                                            break;
                                                        case '-':
                                                                {

                                                                    m=Double.parseDouble(s1)-Double.parseDouble(s2);
                                                                    r[j]=Double.toString(m);                                                                       
                                                                }
                                                            break;
                                                    }
                                            }
                                    }
                            }
                    } 
                    boolean c=true;
                    int q=0;
                for(int i=0;i<an.length();i++)
                {
                    double y=(r[i]).charAt(0);
                    if((y<48||y>57)&&y!=32)c=false;
                    if(y>48||y<57)q++;
                }
                for(int i=0;i<an.length();i++)
                {
                    if(r[i].equalsIgnoreCase("0.0")||r[i].equalsIgnoreCase(" "))
                    {
                        
                    }
                    else
                    {
                        if(c==true)
                        {
                            v=Double.parseDouble(r[i]);
                            
                            break;
                        }
                    }
                }
                double z=zp;           
                v=1-(Math.abs(z-v)/z);
                return v; 
            }
        //result printer
        public void rps()
            {
                for(int i=0;i<231;i++)
                    {
                        if(sln[i]!=null)System.out.println("Result="+sln[i]);
                    } 
            }
    }
