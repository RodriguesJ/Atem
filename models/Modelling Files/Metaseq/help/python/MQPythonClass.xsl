<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" version="4.0"/>
<xsl:variable name="lang" select="ja"/>

<!-- root -->
<xsl:template match="/">
 <html>
  <head><title><xsl:value-of select="doc/class" /></title></head>
  <body>
   <h2><xsl:value-of select="doc/class" /> class</h2>
   <xsl:copy-of select="doc/message[@lang='ja']" />
   <div>
    <xsl:apply-templates select="doc/properties" />
    <xsl:apply-templates select="doc/methods" />
   </div>
  </body>
 </html>
</xsl:template>

<!-- property -->
<xsl:template match="doc/properties">
 <a name="__property" />
 <H3>Property</H3>
 <table border="1" cellspacing="0" cellpadding="1" rules="rows">
  <tbody>
   <tr bgcolor="#e0e0e0">
    <th valign="top" style="padding-left=4; padding-right=4;">get</th>
    <th valign="top" style="padding-left=4; padding-right=4;">set</th>
    <th valign="top" align="left">Type</th>
    <th valign="top" align="left">Name</th>
    <th valign="top" align="left">Description</th>
   </tr>
   <xsl:for-each select="property">
    <xsl:variable name="propname"><xsl:value-of select="name"/></xsl:variable>
    <xsl:variable name="bgcol">
     <xsl:if test="position() mod 2 = 0">#edf4f8</xsl:if>
     <xsl:if test="position() mod 2 = 1">#fffff8</xsl:if>
    </xsl:variable>
    <tr bgcolor="{$bgcol}">
     <a name='{$propname}' />
     <td valign="top" align="center"><xsl:choose><xsl:when test="get[.&gt; 0]">o</xsl:when><xsl:otherwise>x</xsl:otherwise></xsl:choose></td>
     <td valign="top" align="center"><xsl:choose><xsl:when test="set[.&gt; 0]">o</xsl:when><xsl:otherwise>x</xsl:otherwise></xsl:choose></td>
     <td valign="top"><xsl:apply-templates select="type" /></td>
     <td valign="top"><xsl:value-of select="name" /></td>
     <td valign="top">
       <xsl:value-of select="message[@lang='ja']" />
       <xsl:if test="values != ''">
        <table border="0" width="100%">
         <tbody>
         <xsl:for-each select="values/value">
          <tr>
           <tb>[<xsl:value-of select="@value" />] </tb>
           <tb><xsl:copy-of select="message[@lang='ja']" /></tb>
          </tr>
         </xsl:for-each>
        </tbody>
       </table>
      </xsl:if>
     </td>
    </tr>
   </xsl:for-each>
  </tbody>
 </table>
</xsl:template>

<!-- method -->
<xsl:template match="doc/methods">
 <h3>Method</h3>
 <!-- link list -->
 <table border="1" cellspacing="0" cellpadding="1" rules="rows">
  <tbody>
   <tr bgcolor="#e0e0e0">
    <th valign="top" align="left">Member</th>
    <th valign="top" align="left">Description</th>
   </tr>
   <xsl:for-each select="method">
    <xsl:variable name="bgcol">
     <xsl:if test="position() mod 2 = 0">#edf4f8</xsl:if>
     <xsl:if test="position() mod 2 = 1">#fffff8</xsl:if>
    </xsl:variable>
    <tr bgcolor="{$bgcol}">
     <td valign="top" style="padding-right=8;">
      <xsl:variable name="methodname"><xsl:value-of select="name"/></xsl:variable>
      <a href='#{$methodname}'><xsl:value-of select="name"/></a>
     </td>
     <td valign="top"><xsl:value-of select="message[@lang='ja']"/></td>
    </tr>
   </xsl:for-each>
  </tbody>
 </table>
 <p></p>
 <!-- description -->
 <xsl:for-each select="method">
  <table border="0" width="100%">
   <tbody>
    <!-- method name -->
    <tr>
     <td bgcolor="#cccccc">
      <xsl:variable name="methodname"><xsl:value-of select="name"/></xsl:variable>
      <a name='{$methodname}' />
      <b><xsl:value-of select="name"/></b>
      <xsl:for-each select="args/arg">
       <xsl:if test="position() = 1">(</xsl:if>
       <xsl:if test="@option='1' and (position()=1 or preceding-sibling::*[position()=1]/@option!='1')">[</xsl:if>
       <xsl:if test="position() &gt; 1">, </xsl:if>
       <xsl:value-of select="name"/>
       <xsl:if test="position() = last() and @option='1'">]</xsl:if>
       <xsl:if test="position() = last()">)</xsl:if>
      </xsl:for-each>
     </td>
    </tr>
    <tr>
     <td><p><xsl:value-of select="message[@lang='ja']"/></p></td>
    </tr>
    <!-- args value -->
    <xsl:if test="args != ''">
     <tr>
      <td>
       <dl>
        <dt><b>引数：</b></dt>
        <dd>
         <table border="0">
          <tbody>
           <xsl:for-each select="args/arg">
            <tr>
             <td valign="top"><xsl:apply-templates select="type"/></td>
             <td valign="top"><em><xsl:value-of select="name"/></em></td>
             <td valign="top">-</td>
             <td valign="top">
              <xsl:copy-of select="message[@lang='ja']"/>
              <xsl:if test="values != ''">
               <br />
               <xsl:for-each select="values/value">
                [<xsl:value-of select="@value" />] <xsl:copy-of select="message[@lang='ja']" /><br />
               </xsl:for-each>
              </xsl:if>
             </td>
            </tr>
           </xsl:for-each>
          </tbody>
         </table>
        </dd>
       </dl>
      </td>
     </tr>
    </xsl:if>
    <!-- return value -->
    <xsl:for-each select="return">
     <tr>
      <td>
       <dl>
        <dt><b>戻り値：</b></dt>
        <dd>
         <table border="0">
          <tbody>
           <tr>
            <td valign="top"><xsl:apply-templates select="type"/></td>
            <td valign="top">-</td>
            <td valign="top">
             <xsl:copy-of select="message[@lang='ja']"/>
             <xsl:if test="values != ''">
              <br />
              <xsl:for-each select="values/value">
               [<xsl:value-of select="@value" />] <xsl:copy-of select="message[@lang='ja']" /><br />
              </xsl:for-each>
             </xsl:if>
            </td>
           </tr>
          </tbody>
         </table>
        </dd>
       </dl>
      </td>
     </tr>
    </xsl:for-each>
    <!-- attention -->
    <xsl:if test="attention!=''">
     <tr>
      <td>
       <dl>
        <dt><b>注意点：</b></dt>
        <dd><xsl:copy-of select="attention[@lang='ja']" /></dd>
       </dl>
      </td>
     </tr>
    </xsl:if>
    <!-- example -->
    <xsl:if test="example!=''">
     <tr>
      <td>
       <dl>
        <dt><b>例：</b></dt>
        <dd><xsl:copy-of select="example[@lang='ja']" /></dd>
       </dl>
      </td>
     </tr>
    </xsl:if>
   </tbody>
  </table>
  <p></p>
 </xsl:for-each>
</xsl:template>

<!-- type -->
<xsl:template match="type">
 <xsl:if test="@template='list'">list&lt;</xsl:if>
 <xsl:choose>
  <xsl:when test=".='MQDocument'"><a href="MQDocument.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQScene'"><a href="MQScene.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQObject'"><a href="MQObject.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQVertex'"><a href="MQVertex.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQFace'"><a href="MQFace.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQMaterial'"><a href="MQMaterial.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQPoint'"><a href="MQPoint.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQCoordinate'"><a href="MQCoordinate.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQAngle'"><a href="MQAngle.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQMatrix'"><a href="MQMatrix.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:when test=".='MQColor'"><a href="MQColor.xml"><xsl:value-of select="."/></a></xsl:when>
  <xsl:otherwise><xsl:value-of select="."/></xsl:otherwise>
 </xsl:choose>
 <xsl:if test="@template='list'">&gt;</xsl:if>
</xsl:template>

</xsl:stylesheet>
